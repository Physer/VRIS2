using Microsoft.AspNetCore.Mvc;

namespace VRIS.API.Controllers
{
    [Route("api/authorization")]
    public class AuthorizationController
    {
        [Route("/api/authorization/get")]
        [HttpGet]
        public string Get(string code) => code;

        [HttpGet]
        [Route("/api/authorization/login")]
        public ActionResult Login() => new RedirectResult("https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=9d02646e-b39f-4e26-b330-e3434a51585c&response_type=code&redirect_uri=http://localhost:50796/api/authorization/get&response_mode=query&scope=user.readbasic.all calendars.readwrite.shared");

        [HttpPost]
        [Route("/api/authorization/token")]
        public string Token(string code) => string.Empty;
    }
}
