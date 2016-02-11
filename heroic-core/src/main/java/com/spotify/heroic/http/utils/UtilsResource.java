/*
 * Copyright (c) 2015 Spotify AB.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.heroic.http.utils;

import com.spotify.heroic.HeroicLifeCycle;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@Path("utils")
public class UtilsResource {
    private final HeroicLifeCycle lifecycle;

    @Inject
    public UtilsResource(final HeroicLifeCycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @GET
    @Path("wait")
    public void wait(@Suspended final AsyncResponse response) {
        lifecycle.register("client wait", new HeroicLifeCycle.StartupHook() {
            @Override
            public void onStartup(HeroicLifeCycle.Context context) throws Exception {
                response.resume(Response.status(Response.Status.OK).entity("started").build());
            }
        });
    }
}
