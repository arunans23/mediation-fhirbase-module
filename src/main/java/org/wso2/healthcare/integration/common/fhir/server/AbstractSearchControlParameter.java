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

package org.wso2.healthcare.integration.common.fhir.server;

import org.wso2.healthcare.integration.common.Constants;
import org.wso2.healthcare.integration.common.OHServerCommonDataHolder;
import org.wso2.healthcare.integration.common.fhir.server.model.HTTPInfo;

import java.util.Iterator;

/**
 * Represents Search control search parameters
 */
public abstract class AbstractSearchControlParameter implements SearchParameter {

    private boolean active = true;
    private final String name;
    private final String defaultValue;

    public AbstractSearchControlParameter(String name) {
        this.name = name;
        this.defaultValue =
                OHServerCommonDataHolder.getInstance().getHealthcareIntegratorEnvironment().
                        getHealthcareIntegratorConfig().getFHIRServerConfig().getDataSourceConfig().getMatchAnyPattern();
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public String getType() {

        return Constants.FHIR_SEARCH_TYPE_SEARCH_CONTROL;
    }

    @Override
    public String getExpression() {

        return null;
    }

    @Override
    public boolean isActive() {

        return active;
    }

    @Override
    public boolean canPreProcess(HTTPInfo httpInfo) {

        return httpInfo.isQueryParamPresent(getName()) && this.active;
    }

    public void setActive(boolean active) {

        this.active = active;
    }

    @Override
    public boolean isBoundedToProfile() {

        return false;
    }

    @Override
    public Iterator<String> getProfiles() {

        return null;
    }

    @Override
    public String getDefaultValue() {

        return defaultValue;
    }
}
