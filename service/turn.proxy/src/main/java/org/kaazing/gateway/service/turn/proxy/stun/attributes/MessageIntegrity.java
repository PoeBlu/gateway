/**
 * Copyright 2007-2016, Kaazing Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaazing.gateway.service.turn.proxy.stun.attributes;

import static org.kaazing.gateway.service.turn.proxy.stun.attributes.AttributeType.MESSAGE_INTEGRITY;

public class MessageIntegrity extends Attribute {

    private String username;
    private String realm;
    private String password;
    protected byte[] value;

    public MessageIntegrity(byte[] key) {
        this.value = key;
        // TODO: decoding of the key
    }

    @Override
    public short getType() {
        return MESSAGE_INTEGRITY.getType();
    }

    @Override
    public short getLength() {
        return 16;
    }

    @Override
    public byte[] getVariable() {
        return value;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getRealm() {
        return realm;
    }
    
    public String getPassword() {
        return password;
    }

}
