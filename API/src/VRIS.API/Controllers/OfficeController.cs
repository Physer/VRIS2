using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using VRIS.Business.Repositories.Office;
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
        private readonly IOfficeRepository _officeRepository;

        /// <inheritdoc cref="OfficeController"/>
        public OfficeController(IOfficeRepository officeRepository)
        {
            _officeRepository = officeRepository;
        }

        /// <summary>
        /// List all the <see cref="Office"/>s
        /// </summary>
        /// <returns></returns>
        [HttpGet, 
            ProducesResponseType(typeof(IEnumerable<Office>), (int) HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult List() => new ObjectResult(_officeRepository.List());

        /// <summary>
        /// Get a specific <see cref="Office"/> by id
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet("{id:int:required}"), 
            ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult GetById([Required] int id)
        {
            var office = _officeRepository.Get(id);
            if (office == null) return new NotFoundResult();
            return new ObjectResult(office);
        }

        /// <summary>
        /// Get a specific <see cref="Office"/> by name
        /// </summary>
        /// <param name="name"></param>
        /// <returns></returns>
        [HttpGet("{name:required}"), 
            ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult GetByName([Required] string name)
        {
            var office = _officeRepository.FindByName(name);
            if (office == null) return new NotFoundResult();
            return new ObjectResult(office);
        }
    }
}
