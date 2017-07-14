using System.Net.Http;
using System.Threading.Tasks;
//using Microsoft.Extensions.Options;
using Microsoft.Graph;
using Microsoft.IdentityModel.Clients.ActiveDirectory;

namespace VRIS.OutlookConnect.Authentication
{
    /// <inheritdoc cref="IAuthenticationProvider"/>
    public class AzureAuthenticationProvider : IAuthenticationProvider
    {
        //private IOptions<MicrosoftGraph> _graphOptions;

        ///// <inheritdoc cref="IAuthenticationProvider"/>
        //public AzureAuthenticationProvider(IOptions<MicrosoftGraph> graphOptions)
        //{
        //    _graphOptions = graphOptions;
        //}

        /// <inheritdoc cref="IAuthenticationProvider"/>
        public async Task AuthenticateRequestAsync(HttpRequestMessage request)
        {
            var clientId = "ae798feb-2a57-4738-8037-2e7d57ac6930";
            var clientSecret = "84fxdktLxiXvax0iI/m1ARB+QaEpd2c8jZD6tQK9Alc=";
            var authContext = new AuthenticationContext("https://login.windows.net/jonhussdev.onmicrosoft.com/oauth2/token");

            var creds = new ClientCredential(clientId, clientSecret);
            var authResult = await authContext.AcquireTokenAsync("https://graph.microsoft.com/", creds);

            request.Headers.Add("Authorization", "Bearer " + authResult.AccessToken);
        }
    }
}