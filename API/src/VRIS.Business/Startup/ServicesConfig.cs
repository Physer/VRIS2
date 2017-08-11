using System.Collections.Generic;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using VRIS.Business.Repositories.Appointments;
using VRIS.Business.Repositories.Office;
using VRIS.Business.Stores.Token;
using VRIS.Domain.Models;
using VRIS.Domain.Models.Appointments;

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
            services.AddSingleton<ITokenStore, TokenStore>();

            // TEMP
            services.AddSingleton<IOfficeRepository, MockOfficeRepository>(provider => new MockOfficeRepository(new List<Office>
            {
                new Office
                {
                    Id = 0,
                    Name = "Ams 5A"
                },
                new Office
                {
                    Id = 1,
                    Name = "Ams 5B"
                }
            }));
            services.AddSingleton<IAppointmentRepository, MockAppointmentRepository>(provider => new MockAppointmentRepository(new List<Appointment>
            {
                new Appointment
                {
                    Id = 0,
                    OfficeId = 0,
                    Subject = "Test appointment"
                }
            }));
        }
    }
}
