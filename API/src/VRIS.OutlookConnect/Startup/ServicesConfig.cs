using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

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

            // Add repositories
        }
    }
}
