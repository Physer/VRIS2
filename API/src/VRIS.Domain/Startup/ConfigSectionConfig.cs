using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;
using VRIS.Domain.ConfigurationModels;

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
            services.Configure<MicrosoftGraphOptions>(configuration.GetSection("MicrosoftGraph"));
            services.AddSingleton(provider => provider.GetService<IOptionsSnapshot<MicrosoftGraphOptions>>()?.Value);
        }
    }
}
