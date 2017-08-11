using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;

namespace VRIS.API.Controllers
{
    [Route("api/authorization")]
    public class AuthorizationController
    {
        private readonly IHttpContextAccessor _httpContextAccessor;

        public AuthorizationController(IHttpContextAccessor httpContextAccessor)
        {
            _httpContextAccessor = httpContextAccessor;
        }

        [Route("/api/authorization/code")]
        [HttpGet]
        public string GetAuthorizationCode(string code) => code;

        [HttpGet]
        [Route("/api/authorization/loginurl")]
        public string GetLoginUrl() => $"https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=9d02646e-b39f-4e26-b330-e3434a51585c&response_type=code&redirect_uri=https://{_httpContextAccessor.HttpContext.Request.Host}/api/authorization/code&response_mode=query&scope=user.readbasic.all calendars.readwrite.shared";

        [HttpPost]
        [Route("/api/authorization/token")]
        public async Task<string> Token(string authorizationCode)
        {
            if(string.IsNullOrWhiteSpace(authorizationCode)) throw new Exception("No code present");

            using (var client = new HttpClient())
            {
                var url = "https://login.microsoftonline.com/common/oauth2/v2.0/token";
                var payload = new List<KeyValuePair<string, string>>();
                payload.Add(new KeyValuePair<string, string>("client_id", "9d02646e-b39f-4e26-b330-e3434a51585c"));
                payload.Add(new KeyValuePair<string, string>("client_secret", "LFBNdRdrGQRQKoam7ckHCwB"));
                payload.Add(new KeyValuePair<string, string>("grant_type", "authorization_code"));
                payload.Add(new KeyValuePair<string, string>("code", authorizationCode));
                payload.Add(new KeyValuePair<string, string>("redirect_uri", "https://vris.azurewebsites.net/api/authorization/code"));
                var response = await client.PostAsync(url, new FormUrlEncodedContent(payload));
                var responseObject = JObject.Parse(await response.Content.ReadAsStringAsync());

                return responseObject?["access_token"] != null ? responseObject["access_token"].Value<string>() : "Unable to parse response";
            }
        }
    }
}
