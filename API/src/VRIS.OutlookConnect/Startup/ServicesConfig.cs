﻿using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Graph;
using VRIS.OutlookConnect.Authentication;

namespace VRIS.OutlookConnect.Startup
{
    /// <summary>
    /// Service configuration for the Outlook Graph API
    /// </summary>
    public struct ServicesConfig
    {
        /// <summary>
        /// Configure services fot the API Outlook Graph API
        /// </summary>
        /// <param name="services"></param>
        /// <param name="configuration"></param>
        public static void Configure(IServiceCollection services, IConfigurationRoot configuration)
        {
            // Add custom services
            services.AddScoped<IGraphServiceClient, GraphServiceClient>();
            services.AddScoped<Test>();

            // Add repositories

            // Add authentication providers
            services.AddSingleton<IAuthenticationProvider, AzureAuthenticationProvider>();
        }
    }
}
