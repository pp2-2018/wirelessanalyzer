package packageBuilder;

import model.RawPackage;

public interface ConfigurationBuilder {
	public PackageBuilderConfiguration build(RawPackage rawPackage);
}
