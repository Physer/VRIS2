using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using VRIS.Domain.Models;
using VRIS.Domain.Models.Appointments;

#pragma warning disable SG0016

namespace VRIS.API.Controllers
{
    /// <summary>
    /// Controller for provicing offcie specific details
    /// </summary>
    [Route("api/[controller]"), Produces("application/json")]
    public class AppointmentController : Controller
    {

        /// <summary>
        /// List all the <see cref="Appointment"/>s for this <see cref="Office"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <returns></returns>
        [HttpGet("{officeId:int:required}"),
             ProducesResponseType(typeof(IEnumerable<Appointment>), (int)HttpStatusCode.OK),
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

        /// <summary>
        /// Gets an <see cref="Appointment"/> by Id
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="appointmentId">The id of the <see cref="Appointment"/> to return</param>
        /// <returns></returns>
        [HttpGet("{officeId:int:required}/{appointmentId:int:required}"),
            ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult Get([Required] int officeId, [Required] int appointmentId) => new ObjectResult(new SlotAppointment
        {
            Id = appointmentId,
            Subject = "Test2",
            StartUtc = DateTime.Now.ToUniversalTime(),
            EndUtc = DateTime.Now.ToUniversalTime().AddHours(2),
            OfficeId = 0
        });

        /// <summary>
        /// Posts a new <see cref="Appointment"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="appointment">A new appointment <see cref="Appointment"/> to create</param>
        /// <returns></returns>
        [HttpPost("{officeId:int:required}"),
            ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.Created),
            ProducesResponseType((int)HttpStatusCode.NotAcceptable)]
        public IActionResult Post([Required] int officeId, [Required, FromBody] Appointment appointment) => new ObjectResult(appointment);

        /// <summary>
        /// Updates a new <see cref="Appointment"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="appointment">An appointment <see cref="Appointment"/> to update</param>
        /// <returns></returns>
        [HttpPut("{officeId:int:required}"),
            ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult Put([Required] int officeId, [Required, FromBody] Appointment appointment) => new ObjectResult(appointment);

        /// <summary>
        /// Deletes an <see cref="Appointment"/> by id
        /// </summary>
        /// <param name="appointmentId">The id of the <see cref="Appointment"/> to return</param>
        /// <returns></returns>
        [HttpDelete("{appointmentId:int:required}"),
            ProducesResponseType((int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult Delete([Required] int appointmentId) => new StatusCodeResult((int)HttpStatusCode.OK);
    }
}