﻿using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

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

            // Add custom services
            services
                .AddMvcCore()
                .AddJsonFormatters()
                .AddApiExplorer();

            // Add repositories
        }
    }
}
