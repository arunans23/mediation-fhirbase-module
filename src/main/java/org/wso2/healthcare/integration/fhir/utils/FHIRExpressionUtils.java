/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.org).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.healthcare.integration.fhir.utils;

import org.apache.axiom.om.OMElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.util.xpath.SynapseXPath;
import org.jaxen.JaxenException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains utilities related for expression evaluation
 */
public class FHIRExpressionUtils {

    private static final Log log = LogFactory.getLog(FHIRExpressionUtils.class);

    /**
     * Utility function to evaluate given xpath expression against message context envelop and return the result
     *
     * @param xpath
     * @param synCtx
     * @return
     */
    public static ArrayList<OMElement> evaluateXPath(String xpath, MessageContext synCtx) {
        ArrayList<OMElement> resultOMList = new ArrayList<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("Evaluating xpath expression: " + xpath + " against:" + synCtx.getEnvelope());
            }
            SynapseXPath synapsePath = new SynapseXPath(xpath);
            Object result = synapsePath.evaluate(synCtx);
            if (result instanceof List) {
                List resultList = (List) result;
                for (Object obj : resultList) {
                    if (obj instanceof OMElement) {
                        resultOMList.add((OMElement) obj);
                    }
                }
                return resultOMList;
            } else {
                log.warn("Unknown result type");
            }
        } catch (JaxenException e) {
            FHIRConnectorUtils.handleException("Error occurred while evaluating XPath: "
                    + xpath + " against:" + synCtx.getEnvelope(), e);
        }
        return null;
    }
}
