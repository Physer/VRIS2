using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using VRIS.Domain.Models;
using VRIS.Domain.Models.Appointments;

namespace VRIS.API.Controllers
{
    /// <summary>
    /// Controller for provicing offcie specific details
    /// </summary>
    [Route("api/[controller]"), Produces("application/json")]
    public class OfficeCalendarController : Controller
    {
        /// <summary>
        /// List all the <see cref="Appointment"/>s for this <see cref="Office"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <returns></returns>
        [HttpGet("{officeId:int:required}"), 
            ProducesResponseType(typeof(IEnumerable<Appointment>), (int) HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult List([Required] int officeId) => List(officeId, DateTime.UtcNow);

        /// <summary>
        /// List all the <see cref="Appointment"/>s for this <see cref="Office"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="date">The day to show the <see cref="Appointment"/>s from</param>
        /// <returns></returns>
        [HttpGet("{officeId:int:required}/{date:datetime:required}"), 
            ProducesResponseType(typeof(IEnumerable<Appointment>), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.BadRequest),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult List([Required] int officeId, [Required] DateTime date) => new ObjectResult(new[] {
            new Appointment
            {
                Id = 99,
                Subject = "Test",
                OfficeId = officeId
            },
            new SlotAppointment
            {
                Id = 949,
                Subject = "Test2",
                StartUtc = date.ToUniversalTime(),
                EndUtc = date.ToUniversalTime().AddHours(2),
                OfficeId = officeId
            }
        });

        // todo get single appointment? or just get data from another api
    }
}