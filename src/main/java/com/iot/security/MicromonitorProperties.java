package com.iot.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "micromonitor", ignoreUnknownFields = false)
public class MicromonitorProperties {
 
  private final MicromonitorProperties.Async async = new MicromonitorProperties.Async();
  private final MicromonitorProperties.Http http = new MicromonitorProperties.Http();
  private final MicromonitorProperties.Cache cache = new MicromonitorProperties.Cache();
  private final MicromonitorProperties.Mail mail = new MicromonitorProperties.Mail();
  private final MicromonitorProperties.Security security = new MicromonitorProperties.Security();
  private final MicromonitorProperties.Swagger swagger = new MicromonitorProperties.Swagger();
  private final MicromonitorProperties.Metrics metrics = new MicromonitorProperties.Metrics();
  private final MicromonitorProperties.Logging logging = new MicromonitorProperties.Logging();
  private final CorsConfiguration cors = new CorsConfiguration();
  private final MicromonitorProperties.Social social = new MicromonitorProperties.Social();
  private final MicromonitorProperties.Gateway gateway = new MicromonitorProperties.Gateway();
  private final MicromonitorProperties.Registry registry = new MicromonitorProperties.Registry();
  private final MicromonitorProperties.ClientApp clientApp = new MicromonitorProperties.ClientApp();

  public MicromonitorProperties() {
  }

  public MicromonitorProperties.Async getAsync() {
    return this.async;
  }

  public MicromonitorProperties.Http getHttp() {
    return this.http;
  }

  public MicromonitorProperties.Cache getCache() {
    return this.cache;
  }

  public MicromonitorProperties.Mail getMail() {
    return this.mail;
  }

  public MicromonitorProperties.Registry getRegistry() {
    return this.registry;
  }

  public MicromonitorProperties.Security getSecurity() {
    return this.security;
  }

  public MicromonitorProperties.Swagger getSwagger() {
    return this.swagger;
  }

  public MicromonitorProperties.Metrics getMetrics() {
    return this.metrics;
  }

  public MicromonitorProperties.Logging getLogging() {
    return this.logging;
  }

  public CorsConfiguration getCors() {
    return this.cors;
  }

  public MicromonitorProperties.Social getSocial() {
    return this.social;
  }

  public MicromonitorProperties.Gateway getGateway() {
    return this.gateway;
  }

  public MicromonitorProperties.ClientApp getClientApp() {
    return this.clientApp;
  }

  public static class ClientApp {
    private String name = "jhipsterApp";

    public ClientApp() {
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public static class Registry {
    private String password;

    public Registry() {
      this.password = PersoInfoDefaults.Registry.password;
    }

    public String getPassword() {
      return this.password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

  public static class Gateway {
    private final MicromonitorProperties.Gateway.RateLimiting rateLimiting = new MicromonitorProperties.Gateway.RateLimiting();
    private Map<String, List<String>> authorizedMicroservicesEndpoints;

    public Gateway() {
      this.authorizedMicroservicesEndpoints = PersoInfoDefaults.Gateway.authorizedMicroservicesEndpoints;
    }

    public MicromonitorProperties.Gateway.RateLimiting getRateLimiting() {
      return this.rateLimiting;
    }

    public Map<String, List<String>> getAuthorizedMicroservicesEndpoints() {
      return this.authorizedMicroservicesEndpoints;
    }

    public void setAuthorizedMicroservicesEndpoints(Map<String, List<String>> authorizedMicroservicesEndpoints) {
      this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
    }

    public static class RateLimiting {
      private boolean enabled = false;
      private long limit = 100000L;
      private int durationInSeconds = 3600;

      public RateLimiting() {
      }

      public boolean isEnabled() {
        return this.enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public long getLimit() {
        return this.limit;
      }

      public void setLimit(long limit) {
        this.limit = limit;
      }

      public int getDurationInSeconds() {
        return this.durationInSeconds;
      }

      public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
      }
    }
  }

  public static class Social {
    private String redirectAfterSignIn = "/#/home";

    public Social() {
    }

    public String getRedirectAfterSignIn() {
      return this.redirectAfterSignIn;
    }

    public void setRedirectAfterSignIn(String redirectAfterSignIn) {
      this.redirectAfterSignIn = redirectAfterSignIn;
    }
  }

  public static class Logging {
    private boolean useJsonFormat = false;
    private final MicromonitorProperties.Logging.Logstash logstash = new MicromonitorProperties.Logging.Logstash();

    public Logging() {
    }

    public boolean isUseJsonFormat() {
      return this.useJsonFormat;
    }

    public void setUseJsonFormat(boolean useJsonFormat) {
      this.useJsonFormat = useJsonFormat;
    }

    public MicromonitorProperties.Logging.Logstash getLogstash() {
      return this.logstash;
    }

    public static class Logstash {
      private boolean enabled = false;
      private String host = "localhost";
      private int port = 5000;
      private int queueSize = 512;

      public Logstash() {
      }

      public boolean isEnabled() {
        return this.enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getHost() {
        return this.host;
      }

      public void setHost(String host) {
        this.host = host;
      }

      public int getPort() {
        return this.port;
      }

      public void setPort(int port) {
        this.port = port;
      }

      public int getQueueSize() {
        return this.queueSize;
      }

      public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
      }
    }
  }

  public static class Metrics {
    private final MicromonitorProperties.Metrics.Logs logs = new MicromonitorProperties.Metrics.Logs();

    public Metrics() {
    }

    public MicromonitorProperties.Metrics.Logs getLogs() {
      return this.logs;
    }

    public static class Logs {
      private boolean enabled = false;
      private long reportFrequency = 60L;

      public Logs() {
      }

      public boolean isEnabled() {
        return this.enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public long getReportFrequency() {
        return this.reportFrequency;
      }

      public void setReportFrequency(long reportFrequency) {
        this.reportFrequency = reportFrequency;
      }
    }
  }

  public static class Swagger {
    private String title = "Application API";
    private String description = "API documentation";
    private String version = "0.0.1";
    private String termsOfServiceUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String license;
    private String licenseUrl;
    private String defaultIncludePattern;
    private String host;
    private String[] protocols;
    private boolean useDefaultResponseMessages;

    public Swagger() {
      this.termsOfServiceUrl = PersoInfoDefaults.Swagger.termsOfServiceUrl;
      this.contactName = PersoInfoDefaults.Swagger.contactName;
      this.contactUrl = PersoInfoDefaults.Swagger.contactUrl;
      this.contactEmail = PersoInfoDefaults.Swagger.contactEmail;
      this.license = PersoInfoDefaults.Swagger.license;
      this.licenseUrl = PersoInfoDefaults.Swagger.licenseUrl;
      this.defaultIncludePattern = "/api/.*";
      this.host = PersoInfoDefaults.Swagger.host;
      this.protocols = PersoInfoDefaults.Swagger.protocols;
      this.useDefaultResponseMessages = true;
    }

    public String getTitle() {
      return this.title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return this.description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getVersion() {
      return this.version;
    }

    public void setVersion(String version) {
      this.version = version;
    }

    public String getTermsOfServiceUrl() {
      return this.termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
      this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getContactName() {
      return this.contactName;
    }

    public void setContactName(String contactName) {
      this.contactName = contactName;
    }

    public String getContactUrl() {
      return this.contactUrl;
    }

    public void setContactUrl(String contactUrl) {
      this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
      return this.contactEmail;
    }

    public void setContactEmail(String contactEmail) {
      this.contactEmail = contactEmail;
    }

    public String getLicense() {
      return this.license;
    }

    public void setLicense(String license) {
      this.license = license;
    }

    public String getLicenseUrl() {
      return this.licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
      this.licenseUrl = licenseUrl;
    }

    public String getDefaultIncludePattern() {
      return this.defaultIncludePattern;
    }

    public void setDefaultIncludePattern(String defaultIncludePattern) {
      this.defaultIncludePattern = defaultIncludePattern;
    }

    public String getHost() {
      return this.host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public String[] getProtocols() {
      return this.protocols;
    }

    public void setProtocols(String[] protocols) {
      this.protocols = protocols;
    }

    public boolean isUseDefaultResponseMessages() {
      return this.useDefaultResponseMessages;
    }

    public void setUseDefaultResponseMessages(boolean useDefaultResponseMessages) {
      this.useDefaultResponseMessages = useDefaultResponseMessages;
    }
  }

  public static class Security {
    private final MicromonitorProperties.Security.ClientAuthorization clientAuthorization = new MicromonitorProperties.Security.ClientAuthorization();
    private final MicromonitorProperties.Security.Authentication authentication = new MicromonitorProperties.Security.Authentication();
    private final MicromonitorProperties.Security.RememberMe rememberMe = new MicromonitorProperties.Security.RememberMe();

    public Security() {
    }

    public MicromonitorProperties.Security.ClientAuthorization getClientAuthorization() {
      return this.clientAuthorization;
    }

    public MicromonitorProperties.Security.Authentication getAuthentication() {
      return this.authentication;
    }

    public MicromonitorProperties.Security.RememberMe getRememberMe() {
      return this.rememberMe;
    }

    public static class RememberMe {
      @NotNull
      private String key;

      public RememberMe() {
        this.key = PersoInfoDefaults.Security.RememberMe.key;
      }

      public String getKey() {
        return this.key;
      }

      public void setKey(String key) {
        this.key = key;
      }
    }

    public static class Authentication {
      private final MicromonitorProperties.Security.Authentication.Jwt jwt = new MicromonitorProperties.Security.Authentication.Jwt();

      public Authentication() {
      }

      public MicromonitorProperties.Security.Authentication.Jwt getJwt() {
        return this.jwt;
      }

      public static class Jwt {
        private String secret;
        private String base64Secret;
        private long tokenValidityInSeconds;
        private long tokenValidityInSecondsForRememberMe;

        public Jwt() {
          this.secret = PersoInfoDefaults.Security.Authentication.Jwt.secret;
          this.base64Secret = PersoInfoDefaults.Security.Authentication.Jwt.base64Secret;
          this.tokenValidityInSeconds = 1800L;
          this.tokenValidityInSecondsForRememberMe = 2592000L;
        }

        public String getSecret() {
          return this.secret;
        }

        public void setSecret(String secret) {
          this.secret = secret;
        }

        public String getBase64Secret() {
          return this.base64Secret;
        }

        public void setBase64Secret(String base64Secret) {
          this.base64Secret = base64Secret;
        }

        public long getTokenValidityInSeconds() {
          return this.tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
          this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public long getTokenValidityInSecondsForRememberMe() {
          return this.tokenValidityInSecondsForRememberMe;
        }

        public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
          this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
        }
      }
    }

    public static class ClientAuthorization {
      private String accessTokenUri;
      private String tokenServiceId;
      private String clientId;
      private String clientSecret;

      public ClientAuthorization() {
        this.accessTokenUri = PersoInfoDefaults.Security.ClientAuthorization.accessTokenUri;
        this.tokenServiceId = PersoInfoDefaults.Security.ClientAuthorization.tokenServiceId;
        this.clientId = PersoInfoDefaults.Security.ClientAuthorization.clientId;
        this.clientSecret = PersoInfoDefaults.Security.ClientAuthorization.clientSecret;
      }

      public String getAccessTokenUri() {
        return this.accessTokenUri;
      }

      public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
      }

      public String getTokenServiceId() {
        return this.tokenServiceId;
      }

      public void setTokenServiceId(String tokenServiceId) {
        this.tokenServiceId = tokenServiceId;
      }

      public String getClientId() {
        return this.clientId;
      }

      public void setClientId(String clientId) {
        this.clientId = clientId;
      }

      public String getClientSecret() {
        return this.clientSecret;
      }

      public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
      }
    }
  }

  public static class Mail {
    private boolean enabled = false;
    private String from = "";
    private String baseUrl = "";

    public Mail() {
    }

    public boolean isEnabled() {
      return this.enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public String getFrom() {
      return this.from;
    }

    public void setFrom(String from) {
      this.from = from;
    }

    public String getBaseUrl() {
      return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }
  }

  public static class Cache {
    private final MicromonitorProperties.Cache.Hazelcast hazelcast = new MicromonitorProperties.Cache.Hazelcast();
    private final MicromonitorProperties.Cache.Ehcache ehcache = new MicromonitorProperties.Cache.Ehcache();
    private final MicromonitorProperties.Cache.Infinispan infinispan = new MicromonitorProperties.Cache.Infinispan();
    private final MicromonitorProperties.Cache.Memcached memcached = new MicromonitorProperties.Cache.Memcached();

    public Cache() {
    }

    public MicromonitorProperties.Cache.Hazelcast getHazelcast() {
      return this.hazelcast;
    }

    public MicromonitorProperties.Cache.Ehcache getEhcache() {
      return this.ehcache;
    }

    public MicromonitorProperties.Cache.Infinispan getInfinispan() {
      return this.infinispan;
    }

    public MicromonitorProperties.Cache.Memcached getMemcached() {
      return this.memcached;
    }

    public static class Memcached {
      private boolean enabled = false;
      private String servers = "localhost:11211";
      private int expiration = 300;
      private boolean useBinaryProtocol = true;

      public Memcached() {
      }

      public boolean isEnabled() {
        return this.enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getServers() {
        return this.servers;
      }

      public void setServers(String servers) {
        this.servers = servers;
      }

      public int getExpiration() {
        return this.expiration;
      }

      public void setExpiration(int expiration) {
        this.expiration = expiration;
      }

      public boolean isUseBinaryProtocol() {
        return this.useBinaryProtocol;
      }

      public void setUseBinaryProtocol(boolean useBinaryProtocol) {
        this.useBinaryProtocol = useBinaryProtocol;
      }
    }

    public static class Infinispan {
      private String configFile = "default-configs/default-jgroups-tcp.xml";
      private boolean statsEnabled = false;
      private final MicromonitorProperties.Cache.Infinispan.Local local = new MicromonitorProperties.Cache.Infinispan.Local();
      private final MicromonitorProperties.Cache.Infinispan.Distributed distributed = new MicromonitorProperties.Cache.Infinispan.Distributed();
      private final MicromonitorProperties.Cache.Infinispan.Replicated replicated = new MicromonitorProperties.Cache.Infinispan.Replicated();

      public Infinispan() {
      }

      public String getConfigFile() {
        return this.configFile;
      }

      public void setConfigFile(String configFile) {
        this.configFile = configFile;
      }

      public boolean isStatsEnabled() {
        return this.statsEnabled;
      }

      public void setStatsEnabled(boolean statsEnabled) {
        this.statsEnabled = statsEnabled;
      }

      public MicromonitorProperties.Cache.Infinispan.Local getLocal() {
        return this.local;
      }

      public MicromonitorProperties.Cache.Infinispan.Distributed getDistributed() {
        return this.distributed;
      }

      public MicromonitorProperties.Cache.Infinispan.Replicated getReplicated() {
        return this.replicated;
      }

      public static class Replicated {
        private long timeToLiveSeconds = 60L;
        private long maxEntries = 100L;

        public Replicated() {
        }

        public long getTimeToLiveSeconds() {
          return this.timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(long timeToLiveSeconds) {
          this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public long getMaxEntries() {
          return this.maxEntries;
        }

        public void setMaxEntries(long maxEntries) {
          this.maxEntries = maxEntries;
        }
      }

      public static class Distributed {
        private long timeToLiveSeconds = 60L;
        private long maxEntries = 100L;
        private int instanceCount = 1;

        public Distributed() {
        }

        public long getTimeToLiveSeconds() {
          return this.timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(long timeToLiveSeconds) {
          this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public long getMaxEntries() {
          return this.maxEntries;
        }

        public void setMaxEntries(long maxEntries) {
          this.maxEntries = maxEntries;
        }

        public int getInstanceCount() {
          return this.instanceCount;
        }

        public void setInstanceCount(int instanceCount) {
          this.instanceCount = instanceCount;
        }
      }

      public static class Local {
        private long timeToLiveSeconds = 60L;
        private long maxEntries = 100L;

        public Local() {
        }

        public long getTimeToLiveSeconds() {
          return this.timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(long timeToLiveSeconds) {
          this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public long getMaxEntries() {
          return this.maxEntries;
        }

        public void setMaxEntries(long maxEntries) {
          this.maxEntries = maxEntries;
        }
      }
    }

    public static class Ehcache {
      private int timeToLiveSeconds = 3600;
      private long maxEntries = 100L;

      public Ehcache() {
      }

      public int getTimeToLiveSeconds() {
        return this.timeToLiveSeconds;
      }

      public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
      }

      public long getMaxEntries() {
        return this.maxEntries;
      }

      public void setMaxEntries(long maxEntries) {
        this.maxEntries = maxEntries;
      }
    }

    public static class Hazelcast {
      private int timeToLiveSeconds = 3600;
      private int backupCount = 1;
      private final MicromonitorProperties.Cache.Hazelcast.ManagementCenter managementCenter = new MicromonitorProperties.Cache.Hazelcast.ManagementCenter();

      public Hazelcast() {
      }

      public MicromonitorProperties.Cache.Hazelcast.ManagementCenter getManagementCenter() {
        return this.managementCenter;
      }

      public int getTimeToLiveSeconds() {
        return this.timeToLiveSeconds;
      }

      public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
      }

      public int getBackupCount() {
        return this.backupCount;
      }

      public void setBackupCount(int backupCount) {
        this.backupCount = backupCount;
      }

      public static class ManagementCenter {
        private boolean enabled = false;
        private int updateInterval = 3;
        private String url = "";

        public ManagementCenter() {
        }

        public boolean isEnabled() {
          return this.enabled;
        }

        public void setEnabled(boolean enabled) {
          this.enabled = enabled;
        }

        public int getUpdateInterval() {
          return this.updateInterval;
        }

        public void setUpdateInterval(int updateInterval) {
          this.updateInterval = updateInterval;
        }

        public String getUrl() {
          return this.url;
        }

        public void setUrl(String url) {
          this.url = url;
        }
      }
    }
  }

  public static class Http {
    private final MicromonitorProperties.Http.Cache cache = new MicromonitorProperties.Http.Cache();

    public Http() {
    }

    public MicromonitorProperties.Http.Cache getCache() {
      return this.cache;
    }

    public static class Cache {
      private int timeToLiveInDays = 1461;

      public Cache() {
      }

      public int getTimeToLiveInDays() {
        return this.timeToLiveInDays;
      }

      public void setTimeToLiveInDays(int timeToLiveInDays) {
        this.timeToLiveInDays = timeToLiveInDays;
      }
    }
  }

  public static class Async {
    private int corePoolSize = 2;
    private int maxPoolSize = 50;
    private int queueCapacity = 10000;

    public Async() {
    }

    public int getCorePoolSize() {
      return this.corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
      this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
      return this.maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
      this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity() {
      return this.queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
      this.queueCapacity = queueCapacity;
    }
  }
}
