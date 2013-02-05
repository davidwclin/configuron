# Configuron

Configuron is a Clojure library for managing hierarchical properties for deployment under different environments.

Sample configs.clj:

	{"default" {:max-workers 5
	            :temp-dir "/tmp"
	            :async? true
	            :google-analytics? false
	            :aws-key "xxxxx"
	            :port 8090}
	 "dev" {:temp-dir "c:/temp"}
	 "devweb" {:port 5000}
	 "devtest" {:async? false}
	 "prod" {:google-analytics? true}
	 "prodheroku" {:aws-key "yyyyy"}}
	 
Environment hierarchy for the above configs.clj:

	         default
	         /     \
	      dev     prod
	     /   \        \
	devweb  devtest  prodheroku

Properties are picked in this order of precedence:

1. environment variables
2. Leiningen project map
3. concrete environment "prodheroku" (in configs.clj)
4. abstract environment "prod" (in configs.clj)
5. "default" (in configs.clj)

The environment name being used (e.g. "prodherouku") <b>must</b> be specified either as an environment variable (ENV_NAME) or as in the Leiningen project map under key :env-name.

If you want to be able to draw settings from the Leiningen project map, you'll need the following plugin and hook:

	:plugins [[environ/environ.lein "0.3.0"]]
	:hooks [environ.leiningen.hooks]

## Usage

1. create and populate resources/configs.clj
2. add resources dir in classpath in project.clj

	<pre>:resource-paths ["resources"]</pre>
3. specify environment name as environment variable

	<pre>export ENV_NAME="devweb"</pre>
	or in the Leiningen project map in project.clj
	
	<pre>:profiles {:devweb {:env {:env-name "devweb"}}}</pre>
4. if specifying environment name in Leiningen project map, launch with corresponding profile

	<pre>lein with-profile devweb ring server</pre>

## License

Copyright © 2013 David Lin

Distributed under the Eclipse Public License, the same as Clojure.
