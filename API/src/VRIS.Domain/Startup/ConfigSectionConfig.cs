using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace VRIS.Domain.Startup
{
    /// <summary>
    /// Service configuration for the Outlook Graph API
    /// </summary>
    public struct ConfigSectionConfig
    {
        /// <summary>
        /// Configure services fot the API Outlook Graph API
        /// </summary>
        /// <param name="services"></param>
        /// <param name="configuration"></param>
        public static void Configure(IServiceCollection services, IConfigurationRoot configuration)
        {
            // services.Configure<MicrosoftGraph>(_ => configuration.GetSection("MicrosoftGraph"));
        }
    }
}
