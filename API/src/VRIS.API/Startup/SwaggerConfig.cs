using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Swashbuckle.AspNetCore.Swagger;

namespace Valtech.Labs.ValtechPointer.Portal.Application
{
    /// <summary>
    /// Services configuration for Swagger specifically 
    /// </summary>
    public class SwaggerConfig
    {
        private static readonly Info SwaggerInfo = new Info
        {
            Version = "v1",
            Title = "VRIS API",
            Description = "Vergaderruimte Reserverings Informatie Systeem",
            TermsOfService = "None"
        };

        /// <summary>
        /// Setup Swagger for this API
        /// </summary>
        /// <param name="services"></param>
        /// <param name="pathToDoc"></param>
        public static void ConfigureSwaggerServices(IServiceCollection services, string pathToDoc)
        {
            services.AddSwaggerGen(options =>
            {
                options.SwaggerDoc(SwaggerInfo.Version, SwaggerInfo);
                options.IncludeXmlComments(pathToDoc);
                options.DescribeAllEnumsAsStrings();
            });
        }

        /// <summary>
        /// Add Swagger to the VRIS application
        /// </summary>
        /// <param name="applicationBuilder"></param>
        public static void ConfigureSwaggerApplication(IApplicationBuilder applicationBuilder)
        {
            applicationBuilder.UseSwagger();
            applicationBuilder.UseSwaggerUI(swaggerOptions =>
            {
                swaggerOptions.SwaggerEndpoint($"/swagger/{SwaggerInfo.Title}/swagger.json", SwaggerInfo.Title);
            });
        }
    }
}
