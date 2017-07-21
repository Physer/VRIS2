using Microsoft.AspNetCore.Mvc;

namespace VRIS.API.Controllers
{
    [Route("api/authorization")]
    public class AuthorizationController
    {
        [Route("/api/authorization/get")]
        [HttpGet]
        public void Get(string code)
        {
            var x = code;
        }
    }
}
