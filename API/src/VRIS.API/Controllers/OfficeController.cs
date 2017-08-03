using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using VRIS.Business.Repositories.Office;
using VRIS.Domain.Models;

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
        /// Get a specific <see cref="Office"/> by officeId
        /// </summary>
        /// <param name="officeId"></param>
        /// <returns></returns>
        [HttpGet("{officeId:int:required}"), 
            ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult GetById([Required] int officeId)
        {
            var office = _officeRepository.Read(officeId);
            if (office == null) return new ContentResult
            {
                Content = $"No office found with id {officeId}",
                StatusCode = (int)HttpStatusCode.NotFound
            };
            return new ObjectResult(office);
        }

        /// <summary>
        /// Get a specific <see cref="Office"/> by officeName
        /// </summary>
        /// <param name="officeName"></param>
        /// <returns></returns>
        [HttpGet("{officeName:required}"), 
            ProducesResponseType(typeof(Office), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult GetByName([Required] string officeName)
        {
            var office = _officeRepository.FindByName(officeName);
            if (office == null) return new ContentResult
            {
                Content = $"No office found with id {officeName}",
                StatusCode = (int)HttpStatusCode.NotFound
            };
            return new ObjectResult(office);
        }
    }
}
