/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.service;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import io.fabric8.api.PlaceholderResolver;
import io.fabric8.api.jcip.ThreadSafe;
import io.fabric8.api.scr.AbstractComponent;
@ThreadSafe
@Component(name = "io.fabric8.placholder.resolver.env", label = "Fabric8 Environment Placeholder Resolver", metatype = false)
@Service(PlaceholderResolver.class)
public final class EnvPlaceholderResolver extends AbstractComponent implements PlaceholderResolver {

    private static final String ENV_SCHEME = "env";

    @Activate
    void activate() {
        activateComponent();
    }

    @Deactivate
    void deactivate() {
        deactivateComponent();
    }

    @Override
    public String getScheme() {
        return ENV_SCHEME;
    }

    @Override
    public String resolve(Map<String, Map<String, String>> configs, String pid, String key, String value) {
        assertValid();
        if (value != null && value.length() > ENV_SCHEME.length())  {
            String name = value.substring(ENV_SCHEME.length() + 1);
            return System.getenv(name);
        }
        return value;
    }
}
