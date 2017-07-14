using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using VRIS.Domain.Models;

namespace VRIS.API.Controllers
{
    /// <summary>
    /// Controller for provicing <see cref="Office"/> specific details
    /// </summary>
    [Route("api/[controller]"), Produces("application/json")]
    public class OfficeController : Controller
    {
        /// <summary>
        /// List all the <see cref="Office"/>s
        /// </summary>
        /// <returns></returns>
        [HttpGet, ProducesResponseType(typeof(IEnumerable<Office>), (int)HttpStatusCode.OK)]
        public IEnumerable<Office> List() => new [] {
            new Office
            {
                Id = 0,
                Name = "5B"
            }
        };

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
