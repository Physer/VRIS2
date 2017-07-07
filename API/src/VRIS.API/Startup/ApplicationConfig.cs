using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;

namespace VRIS.API.Startup
{
    /// <summary>
    /// Application Config for the VRIS Api
    /// </summary>
    public struct ApplicationConfig
    {
        /// <summary>
        /// Setup Dotnet Core App
        /// </summary>
        /// <param name="applicationBuilder"></param>
        /// <param name="environment"></param>
        /// <param name="loggerFactory"></param>
        /// <param name="configuration"></param>
        public static void Configure(IApplicationBuilder applicationBuilder, IHostingEnvironment environment, 
            ILoggerFactory loggerFactory, IConfigurationRoot configuration)
        {
            loggerFactory.AddConsole(configuration.GetSection("Logging"));
            loggerFactory.AddDebug();

            if (environment.IsDevelopment())
            {
                applicationBuilder.UseDeveloperExceptionPage();
                SwaggerConfig.ConfigureSwaggerApplication(applicationBuilder);
            }
            else
            {
                applicationBuilder.UseExceptionHandler("/Home/Error");
            }

            applicationBuilder.UseMvc(routes =>
            {
                routes.MapRoute(
                    name: "areaRoute",
                    template: "{area:exists}/{controller=Admin}/{action=Index}/{id?}");

                routes.MapRoute(
                    name: "default",
                    template: "{controller=Home}/{action=Index}/{id?}");
            });
        }
    }
}
