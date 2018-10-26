/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2018 The ZAP Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.zap.extension.hud.ui.specific;

import io.github.bonigarcia.Options;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.zaproxy.zap.extension.hud.ui.generic.LeftPanelUnitTest;
import org.zaproxy.zap.extension.hud.ui.generic.RightPanelUnitTest;

@Disabled
@ExtendWith(SeleniumExtension.class)
public class ExampleDotComChromeUnitTest {

    static final String TARGET = "https://www.example.com";
    // TODO parameterise the host:port
    static final String PROXY_HOST = "localhost";
    static final int PROXY_PORT = 8999;
    static final String PROXY_HOST_PORT = PROXY_HOST + ":" + PROXY_PORT;

    @Options ChromeOptions chromeOptions = new ChromeOptions();

    {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(PROXY_HOST_PORT)
                .setFtpProxy(PROXY_HOST_PORT)
                .setSslProxy(PROXY_HOST_PORT);

        chromeOptions.setCapability(CapabilityType.PROXY, proxy);
        chromeOptions.setCapability("acceptInsecureCerts", true);
        chromeOptions.setHeadless(true);
    }

    @Test
    public void frameUnitTests(ChromeDriver driver) throws InterruptedException {
        driver.get(TARGET);
        // Give the HUD a chance to load
        Thread.sleep(5000);

        LeftPanelUnitTest.runAllTests(driver);
        RightPanelUnitTest.runAllTests(driver);
        // TODO add more tests
    }
}
