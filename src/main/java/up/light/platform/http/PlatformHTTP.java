package up.light.platform.http;

import up.light.platform.IContextHandler;
import up.light.platform.IDriverGenerator;
import up.light.platform.IPlatform;

public class PlatformHTTP implements IPlatform {

	@Override
	public String getName() {
		return "http";
	}

	@Override
	public IDriverGenerator getGenerator() {
		return null;
	}

	@Override
	public IContextHandler getContextHandler() {
		return null;
	}

}
