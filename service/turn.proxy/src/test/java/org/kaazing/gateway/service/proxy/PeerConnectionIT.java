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
package org.kaazing.gateway.service.proxy;

import static org.kaazing.test.util.ITUtil.createRuleChain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.kaazing.gateway.server.test.GatewayRule;
import org.kaazing.gateway.server.test.config.GatewayConfiguration;
import org.kaazing.gateway.server.test.config.builder.GatewayConfigurationBuilder;
import org.kaazing.gateway.util.feature.EarlyAccessFeatures;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

public class PeerConnectionIT {

    private final K3poRule k3po = new K3poRule()
            .setScriptRoot("org/kaazing/specification/turn/peer.connection")
            .scriptProperty("acceptURI 'tcp://localhost:3479'");

    private final GatewayRule gateway = new GatewayRule() {
        {
            // @formatter:off
            GatewayConfiguration configuration =
                    new GatewayConfigurationBuilder()
                        .property(EarlyAccessFeatures.TURN_PROXY.getPropertyName(), "true")
                        .service()
                            .accept("tcp://localhost:3478")
                            .connect("tcp://localhost:3479")
                            .type("turn.proxy")
                        .done()
                    .done();
            // @formatter:on
            init(configuration);
        }
    };

    @Rule
    public TestRule chain = createRuleChain(gateway, k3po);

    /**
     * See <a href="https://tools.ietf.org/html/rfc5766">RFC 5766: Turn Protocol</a>.
     */
    @Test
    @Specification({
        "correct.turn.protocol/request",
        "correct.turn.protocol/response"})
    public void shouldSucceedWithCorrectTURNProcess() throws Exception {
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc5766">RFC 5766: Turn Protocol</a>.
     */
    @Test
    @Specification({
        "no.peer.address.with.permissions.responds.400/request",
        "no.peer.address.with.permissions.responds.400/response" })
    public void shouldRespond400ToNoPeerAddressInPermissionRequest() throws Exception {
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc5766">RFC 5766: Turn Protocol</a>.
     */
    @Test
    @Specification({
        "no.channel.number.with.binding.request.responds.400/request",
        "no.channel.number.with.binding.request.responds.400/response" })
    public void shouldRespond400ToNoChannelNumberInBindingRequest() throws Exception {
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc5766">RFC 5766: Turn Protocol</a>.
     */
    @Test
    @Specification({
        "no.peer.address.with.binding.request.responds.400/request",
        "no.peer.address.with.binding.request.responds.400/response" })
    public void shouldRespond400ToNoPeerAddressInBindingRequest() throws Exception {
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc5766">RFC 5766: Turn Protocol</a>.
     */
    @Test
    @Specification({
        "invalid.channel.number.responds.400/request",
        "invalid.channel.number.responds.400/response" })
    public void shouldRespond400ToInvalidChannelNumber() throws Exception {
        k3po.finish();
    }

    /**
     * See <a href="https://tools.ietf.org/html/rfc5766">RFC 5766: Turn Protocol</a>.
     */
    @Test
    @Specification({
        "correct.turn.protocol.with.sent.data/request",
        "correct.turn.protocol.with.sent.data/response" })
    public void shouldSuccessfullySendData() throws Exception {
        k3po.finish();
    }


}
