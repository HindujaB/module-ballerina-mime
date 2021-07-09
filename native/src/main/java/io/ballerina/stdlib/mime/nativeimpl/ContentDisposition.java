/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package io.ballerina.stdlib.mime.nativeimpl;

import io.ballerina.runtime.api.creators.ValueCreator;
import io.ballerina.runtime.api.utils.StringUtils;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.api.values.BString;
import io.ballerina.stdlib.mime.util.MimeUtil;

import static io.ballerina.stdlib.mime.util.MimeConstants.CONTENT_DISPOSITION_STRUCT;
import static io.ballerina.stdlib.mime.util.MimeConstants.DISPOSITION_FIELD;

/**
 * Functionality related to content disposition.
 *
 * @since 1.1.0
 */
public class ContentDisposition {

    public static BObject getContentDispositionObject(BString contentDisposition) {
        BObject contentDispositionObj = ValueCreator.createObjectValue(MimeUtil.getMimePackage(),
                                                                       CONTENT_DISPOSITION_STRUCT);
        MimeUtil.populateContentDispositionObject(contentDispositionObj, contentDisposition.getValue());
        return contentDispositionObj;
    }

    public static BString convertContentDispositionToString(BObject contentDispositionObj) {
        StringBuilder dispositionBuilder = new StringBuilder();
        String disposition = String.valueOf(contentDispositionObj.get(DISPOSITION_FIELD));
        if (!disposition.isEmpty()) {
            dispositionBuilder.append(disposition);
            MimeUtil.convertDispositionObjectToString(dispositionBuilder, contentDispositionObj);
        }
        return StringUtils.fromString(dispositionBuilder.toString());
    }

    private ContentDisposition() {}
}
