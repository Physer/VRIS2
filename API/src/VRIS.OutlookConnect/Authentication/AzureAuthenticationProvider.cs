using System.Linq;
using System.Net.Http;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
//using Microsoft.Extensions.Options;
using Microsoft.Graph;
using Microsoft.IdentityModel.Clients.ActiveDirectory;
using VRIS.Domain.ConfigurationModels;

namespace VRIS.OutlookConnect.Authentication
{
    /// <inheritdoc cref="IAuthenticationProvider"/>
    public class AzureAuthenticationProvider : IAuthenticationProvider
    {
        private readonly MicrosoftGraphOptions _graphOptions;
        private readonly X509Certificate2 _certificate;

        /// <inheritdoc cref="IAuthenticationProvider"/>
        public AzureAuthenticationProvider(MicrosoftGraphOptions graphOptions)
        {
            _graphOptions = graphOptions;
            var certPath = @"C:\Repositories\Labs\VRIS2\API\src\VRIS.OutlookConnect\cert (2).pfx";
            var certPass = "1234";

            // Create a collection object and populate it using the PFX file
            var collection = new X509Certificate2Collection();
            collection.Import(certPath, certPass, X509KeyStorageFlags.PersistKeySet);

            _certificate = collection[0];
        }

        /// <inheritdoc cref="IAuthenticationProvider"/>
        public async Task AuthenticateRequestAsync(HttpRequestMessage request)
        {
            var clientId = _graphOptions.ClientId;
            var clientSecret = _graphOptions.ClientSecret;
            var authContext = new AuthenticationContext(_graphOptions.AuthContextUrl);

            var creds = new ClientCredential(clientId, clientSecret);
            //var creds = new ClientAssertionCertificate(clientId, _certificate);
            var authResult = await authContext.AcquireTokenAsync(_graphOptions.GraphUrl, creds);

            request.Headers.Add("Authorization", $"{authResult.AccessTokenType} {authResult.AccessToken}");


            //ClientCredential credential = new ClientCredential(clientId, clientSecret);
            //var authContext = new AuthenticationContext(_graphOptions.AuthContextUrl);
            ////var result = await authContext.AcquireTokenSilentAsync(_graphOptions.AuthContextUrl, credential,
            ////    new UserIdentifier("139f0669-cebb-42df-b9e3-415009ddb690", UserIdentifierType.UniqueId));
            //var result = await authContext.AcquireTokenAsync(clientId, credential);
            //request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", result.AccessToken);
        }
    }
}