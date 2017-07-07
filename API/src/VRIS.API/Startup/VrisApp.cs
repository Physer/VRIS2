using System;
using System.IO;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Valtech.Labs.ValtechPointer.Portal.Application;

namespace VRIS.API.Startup
{
    /// <summary>
    /// VRIS Application
    /// </summary>
    public class VrisApp
    {
        /// <summary>
        /// Application entry point
        /// </summary>
        /// <param name="args"></param>
        [STAThread] public static void Main(string[] args) => new WebHostBuilder()
            .UseContentRoot(Directory.GetCurrentDirectory()).UseIISIntegration().UseKestrel()
            .UseStartup<VrisApp>().Build().Run();

        /// <summary>
        /// VRIS Application
        /// </summary>
        /// <param name="env"></param>
        public VrisApp(IHostingEnvironment env)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(env.ContentRootPath)
                .AddJsonFile("appsettings.json", optional: false, reloadOnChange: true)
                .AddJsonFile($"appsettings.{env.EnvironmentName}.json", optional: true)
                .AddEnvironmentVariables();
            Configuration = builder.Build();
        }

        /// <inheritdoc cref="IConfigurationRoot"/>
        public IConfigurationRoot Configuration { get; }

        /// <summary>
        /// This method gets called by the runtime. Use this method to add services to the container.
        /// </summary>
        /// <param name="services"></param>
        public void ConfigureServices(IServiceCollection services)
            => ServicesConfig.Configure(services, Configuration);

        /// <summary>
        /// This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        /// </summary>
        /// <param name="app"></param>
        /// <param name="env"></param>
        /// <param name="loggerFactory"></param>
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, ILoggerFactory loggerFactory)
            => ApplicationConfig.Configure(app, env, loggerFactory, Configuration);
    }
}
