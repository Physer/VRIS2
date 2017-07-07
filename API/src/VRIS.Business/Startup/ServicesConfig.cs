using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace VRIS.Business.Startup
{
    /// <summary>
    /// Service configuration for the Services project
    /// </summary>
    public struct ServicesConfig
    {
        /// <summary>
        /// Configure services fot the Services project
        /// </summary>
        /// <param name="services"></param>
        /// <param name="configuration"></param>
        public static void Configure(IServiceCollection services, IConfigurationRoot configuration)
        {
            // Initiate Connections
            OutlookConnect.Startup.ServicesConfig.Configure(services, configuration);

            // Add custom services

            // Add repositories
        }
    }
}
