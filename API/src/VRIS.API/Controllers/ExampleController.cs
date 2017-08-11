using Microsoft.AspNetCore.Mvc;

namespace VRIS.API.Controllers
{
    public class ExampleController : Controller
    {
        [HttpGet]
        [Route("/api/example")]
        public string Get() => "Hello VRIS!";
    }
}
