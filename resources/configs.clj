{"default" {:max-workers (+ 1 4)
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