using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using VRIS.Domain.Models;
using VRIS.OutlookConnect;

namespace VRIS.API.Controllers
{
    /// <summary>
    /// Controller for provicing <see cref="Office"/> specific details
    /// </summary>
    [Route("api/[controller]"), Produces("application/json")]
    public class OfficeController : Controller
    {
        private readonly Test _test;

        /// <inheritdoc cref="OfficeController"/>
        public OfficeController(Test test)
        {
            _test = test;
        }

        /// <summary>
        /// List all the <see cref="Office"/>s
        /// </summary>
        /// <returns></returns>
        [HttpGet, 
            ProducesResponseType(typeof(IEnumerable<Office>), (int) HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public async Task<IActionResult> ListAsync() => new ObjectResult((await _test.GetUserInfoAsync()).Select(
            user => new Office()));

        /// <summary>
        /// Get a specific <see cref="Office"/> by id
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet("{id:int:required}"), 
            ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult GetById([Required] int id) => new ObjectResult(new Office
        {
            Id = 0,
            Name = "5A"
        });

        /// <summary>
        /// Get a specific <see cref="Office"/> by name
        /// </summary>
        /// <param name="name"></param>
        /// <returns></returns>
        [HttpGet("{name:required}"), 
            ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult GetByName([Required] string name) => new ObjectResult(new Office
        {
            Id = 1,
            Name = "5B"
        });
    }
}
