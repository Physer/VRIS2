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
        /// Gets an <see cref="Appointment"/> by Id
        /// </summary>
        /// <param name="appointmentId">The id of the <see cref="Appointment"/> to return</param>
        /// <returns></returns>
        [HttpGet("{appointmentId:int:required}"), 
            ProducesResponseType(typeof(Appointment), (int) HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult Get([Required] int appointmentId) => new ObjectResult(new SlotAppointment
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
        /// <param name="appointment">A new appointment <see cref="Appointment"/> to create</param>
        /// <returns></returns>
        [HttpPost, 
            ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.Created),
            ProducesResponseType((int)HttpStatusCode.NotAcceptable)]
        public IActionResult Post([Required, FromBody] Appointment appointment) => new ObjectResult(appointment);

        /// <summary>
        /// Updates a new <see cref="Appointment"/>
        /// </summary>
        /// <param name="appointment">An appointment <see cref="Appointment"/> to update</param>
        /// <returns></returns>
        [HttpPut, 
            ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.OK),
            ProducesResponseType((int)HttpStatusCode.NotFound)]
        public IActionResult Put([Required, FromBody] Appointment appointment) => new ObjectResult(appointment);

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