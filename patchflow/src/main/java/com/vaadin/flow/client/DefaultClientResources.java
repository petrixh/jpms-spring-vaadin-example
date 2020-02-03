/*
 * Copyright 2000-2020 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.client;

import java.io.InputStream;

import com.vaadin.flow.client.osgi.OsgiClientResources;

/**
 * Default implementation for {@link ClientResources} service which returns the
 * client-side resources via the plain Java
 * {@link Class#getResourceAsStream(String)} method.
 *
 * @author Vaadin Ltd
 * @since 1.2
 */
public final class DefaultClientResources implements ClientResources {

    // Need to patch the DefaultClientResources class in flow-server as it uses the "wrong" class loader to load resources.
    // flow-server class loader cannot read flow-client resources through the class path as they are separate modules.
    // Essentially need to use the class loader from a class that exists in flow-client. At present we have 2 options:
    // OsgiClientResources and OsgiClientStaticResource
    // To use it in the runtime add this to launch script/jvm args
    // add "--patch-module flow.server=<PATH_TO_PATCH_JAR>/patchflow-0.0.1.jar" to startup script
    // for instance when running the app from the web folder:
    // java --show-module-resolution --add-modules java.instrument --patch-module flow.server=../patchflow/target/patchflow-0.0.1.jar --module-path=target/modules --module example.web/red.jackal.training.spring.jpms.web.WebApplication
    //
    // Or when running from IntelliJ:
    // 1: run it once through IntelliJ, copy the first line from the console output (contains the VM options)
    // 2: Open up the runtime configs and paste in the line to "VM Options"
    // 3: Somewhere in the middle (for instance after the "-classpath" add in this line:
    // --patch-module flow.server=patchflow/target/patchflow-0.0.1.jar
    // 4: save and rerun...

    @Override
    public InputStream getResource(String path) {
        return OsgiClientResources.class.getResourceAsStream(path);
    }
}
