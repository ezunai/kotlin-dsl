/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Configures a Gradle Kotlin DSL module.
 *
 * The assembled jar will:
 *  - be named after `base.archivesBaseName`
 *  - include all sources
 */

import accessors.sourceSets

plugins {
    id("kotlin-library")
}

// including all sources
val main by sourceSets
afterEvaluate {
    tasks.getByName<Jar>("jar") {
        from(main.allSource)
        manifest.attributes.apply {
            put("Implementation-Title", "Gradle Kotlin DSL (${project.name})")
            put("Implementation-Version", version)
        }
    }
}

tasks.register("sourcesJar", Jar::class.java) {
    from(main.allSource)
    classifier = "sources"
}
