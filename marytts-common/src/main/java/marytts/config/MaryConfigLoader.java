/**
 * Copyright 2011 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * This file is part of MARY TTS.
 *
 * MARY TTS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package marytts.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import marytts.exceptions.MaryConfigurationException;
import marytts.modules.nlp.phonemiser.AllophoneSet;
import marytts.util.io.PropertiesAccessor;
import marytts.util.io.PropertiesTrimTrailingWhitespace;

/**
 * @author marc
 *
 */
public abstract class MaryConfigLoader {
    protected static final ServiceLoader<MaryConfigLoader> configLoader = ServiceLoader.load(MaryConfigLoader.class);

    protected MaryConfigLoader() throws MaryConfigurationException {
	try {
	    InputStream input_stream = this.getClass().getResourceAsStream(MaryConfigurationFactory.DEFAULT_KEY + ".config");
	    loadConfiguration("default", input_stream);
	} catch (Exception ex) {
	}
    }

    public abstract void loadConfiguration(String set, InputStream input_stream) throws MaryConfigurationException;

    public void load() {
	System.out.println("load " + this.getClass().toString() + "....");
    }


    public static synchronized Iterable<MaryConfigLoader> getConfigLoaders() {
        return configLoader;
    }
}
