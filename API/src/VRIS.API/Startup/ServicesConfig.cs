using System;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using VRIS.Business.HttpFilters;

namespace VRIS.API.Startup
{
    /// <summary>
    /// Service configuration for the API project
    /// </summary>
    public struct ServicesConfig
    {
        /// <summary>
        /// Configure services fot the API project
        /// </summary>
        /// <param name="services"></param>
        /// <param name="configuration"></param>
        public static void Configure(IServiceCollection services, IConfigurationRoot configuration)
        {
            // Initiate other configs
            VRIS.Business.Startup.ServicesConfig.Configure(services, configuration);
            SwaggerConfig.ConfigureSwaggerServices(services, configuration["Swagger:Path"]);

            var showStackTraceSetting = configuration["Debug:ShowStackTrace"];
            var showStackTrace = !String.IsNullOrWhiteSpace(showStackTraceSetting) && 
                Convert.ToBoolean(showStackTraceSetting);

            // Add custom services
            services
                .AddMvcCore(config =>
                    // config.Filters.Add(new AuthorizeFilter(AuthorizationPolicy.Combine()));
                    config.Filters.Add(new GlobalExceptionFilter(showStackTrace)))
                .AddJsonOptions(options =>
                {
                    options.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
                    options.SerializerSettings.NullValueHandling = NullValueHandling.Ignore;
                })
                .AddJsonFormatters()
                .AddApiExplorer();
        }
    }
}
