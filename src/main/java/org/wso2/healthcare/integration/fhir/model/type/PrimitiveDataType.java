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

package org.wso2.healthcare.integration.fhir.model.type;

import org.hl7.fhir.r4.model.PrimitiveType;
import org.hl7.fhir.r4.model.Type;
import org.wso2.healthcare.integration.fhir.FHIRConnectException;

/**
 * DataType wrapper holding FHIR Primitive types
 */
public class PrimitiveDataType extends DataType {

    protected PrimitiveType typeObject;

    public PrimitiveDataType(String objectId, PrimitiveType typeObject) throws FHIRConnectException {
        super(objectId);
        this.typeObject = typeObject;
    }

    @Override
    public Type unwrap() {
        return typeObject;
    }
}
