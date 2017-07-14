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

        public OfficeController(Test test)
        {
            _test = test;
        }

        /// <summary>
        /// List all the <see cref="Office"/>s
        /// </summary>
        /// <returns></returns>
        [HttpGet, ProducesResponseType(typeof(IEnumerable<Office>), (int) HttpStatusCode.OK)]
        public async Task<IEnumerable<Office>> ListAsync() => (await _test.GetUserInfoAsync()).Select(
            user => new Office());

        /// <summary>
        /// Get a specific <see cref="Office"/> by id
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet("{id:int:required}"), ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK)]
        public Office GetById([Required] int id) => new Office
        {
            Id = 0,
            Name = "5A"
        };

        /// <summary>
        /// Get a specific <see cref="Office"/> by name
        /// </summary>
        /// <param name="name"></param>
        /// <returns></returns>
        [HttpGet("{name:required}"), ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK)]
        public Office GetByName([Required] string name) => new Office
        {
            Id = 1,
            Name = "5B"
        };
    }
}
