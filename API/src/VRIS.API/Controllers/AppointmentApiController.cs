using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using VRIS.Business.Repositories.Appointments;
using VRIS.Business.Repositories.Office;
using VRIS.Domain.Models;
using VRIS.Domain.Models.Appointments;

#pragma warning disable SG0016

namespace VRIS.API.Controllers
{
    /// <summary>
    /// Controller for provicing offcie specific details
    /// </summary>
    [Route("api/[controller]"), Produces("application/json")]
    public class AppointmentApiController : Controller
    {

        private readonly IAppointmentRepository _appointmentRepository;
        // temp and fake
        private readonly IOfficeRepository _officeRepository;

        /// <inheritdoc cref="OfficeApiController"/>
        public AppointmentApiController(IAppointmentRepository appointmentRepository, IOfficeRepository officeRepository)
        {
            _appointmentRepository = appointmentRepository;
            _officeRepository = officeRepository;
        }

        /// <summary>
        /// List all the <see cref="Appointment"/>s for this <see cref="Office"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <returns>List of all the <see cref="Appointment"/>s for this <see cref="Office"/></returns>
        /// <response code="200">List of all the <see cref="Appointment"/>s for this <see cref="Office"/></response>
        /// <response code="400">No past dates allowed</response>
        /// <response code="404">
        /// No office found with id {officeId} &#10; 
        /// No appointment found for the office {office.Name}({officeId})
        /// </response>
        [HttpGet("{officeId:int:required}"),
         ProducesResponseType(typeof(IEnumerable<Appointment>), (int)HttpStatusCode.OK),
         ProducesResponseType(typeof(string), (int)HttpStatusCode.BadRequest),
         ProducesResponseType(typeof(string), (int)HttpStatusCode.NotFound)]
        public IActionResult List([Required] int officeId) => List(officeId, DateTime.UtcNow);

        /// <summary>
        /// List all the <see cref="Appointment"/>s for this <see cref="Office"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="date">The day to show the <see cref="Appointment"/>s from</param>
        /// <returns>List of all the <see cref="Appointment"/>s for this <see cref="Office"/></returns>
        /// <response code="200">List of all the <see cref="Appointment"/>s for this <see cref="Office"/></response>
        /// <response code="400">No past dates allowed</response>
        /// <response code="404">
        /// No office found with id {officeId} &#10; 
        /// No appointment found for the office {office.Name}({officeId})
        /// </response>
        [HttpGet("{officeId:int:required}/{date:datetime:required}"),
         ProducesResponseType(typeof(IEnumerable<Appointment>), (int)HttpStatusCode.OK),
         ProducesResponseType(typeof(string), (int)HttpStatusCode.BadRequest),
         ProducesResponseType(typeof(string), (int)HttpStatusCode.NotFound)]
        public IActionResult List([Required] int officeId, [Required] DateTime date)
        {
            var office = _officeRepository.Read(officeId);
            if(office == null) return new ContentResult
            {
                Content = $"No office found with id {officeId}",
                StatusCode = (int)HttpStatusCode.NotFound
            };
            if (date < DateTime.Today) return new ContentResult
            {
                Content = "No past dates allowed",
                StatusCode = (int)HttpStatusCode.BadRequest
            };

            var appointments = _appointmentRepository.List()?.Where(appointment => officeId.Equals(appointment.OfficeId));
            if(appointments == null) return new ContentResult
            {
                Content = $"No appointment found for the office {office.Name}({officeId})",
                StatusCode = (int)HttpStatusCode.NotFound
            };
            return new ObjectResult(appointments);
        }

        /// <summary>
        /// Gets an <see cref="Appointment"/> by Id
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="appointmentId">The id of the <see cref="Appointment"/> to return</param>
        /// <returns><see cref="Appointment"/></returns>
        /// <response code="200"><see cref="Appointment"/></response>
        /// <response code="404">
        /// No office found with id {officeId} &#10; 
        /// No appointment found with the id {appointmentId} for the office {office.Name}({officeId})
        /// </response>
        [HttpGet("{officeId:int:required}/{appointmentId:int:required}"),
         ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.OK),
         ProducesResponseType(typeof(string), (int)HttpStatusCode.NotFound)]
        public IActionResult Get([Required] int officeId, [Required] int appointmentId)
        {
            var office = _officeRepository.Read(officeId);
            if (office == null) return new ContentResult
            {
                Content = $"No office found with id {officeId}",
                StatusCode = (int)HttpStatusCode.NotFound
            };

            var appointment = _appointmentRepository.Read(appointmentId);
            if (appointment == null) return new ContentResult
            {
                Content = $"No appointment found with the id {appointmentId} for the office {office.Name}({officeId})",
                StatusCode = (int)HttpStatusCode.NotFound
            };
            return new ObjectResult(appointment);
        }

        /// <summary>
        /// Posts a new <see cref="Appointment"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="appointment">A new appointment <see cref="Appointment"/> to create</param>
        /// <returns><see cref="Appointment"/></returns>
        /// <response code="200"><see cref="Appointment"/></response>
        /// <response code="404"> No office found with id {officeId} </response>
        /// <response code="405"> You are required to have an <see cref="Appointment"/> in the request body </response>
        /// <response code="406"> New <see cref="Appointment"/> items are not allowed to have an id </response>
        [HttpPost("{officeId:int:required}"),
            ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.Created),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.NotFound),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.MethodNotAllowed),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.NotAcceptable)]
        public IActionResult Post([Required] int officeId, [Required, FromBody] Appointment appointment)
        {
            var office = _officeRepository.Read(officeId);
            if (office == null) return new ContentResult
            {
                Content = $"No office found with id {officeId}",
                StatusCode = (int)HttpStatusCode.NotFound
            };
            if (appointment == null) return new ContentResult
            {
                Content = "You are required to have an appointment in the request body",
                StatusCode = (int)HttpStatusCode.MethodNotAllowed
            };

            try
            {
                var savedAppointment = _appointmentRepository.Create(appointment);
                return new ObjectResult(savedAppointment);
            }
            catch (ArgumentException e)
            {
                Console.WriteLine(e);
                return new ContentResult
                {
                    Content = e.Message,
                    StatusCode = (int)HttpStatusCode.NotAcceptable
                };
            }
        }

        /// <summary>
        /// Updates a new <see cref="Appointment"/>
        /// </summary>
        /// <param name="officeId">The id of the <see cref="Office"/> to list the <see cref="Appointment"/>s from</param>
        /// <param name="appointment">An appointment <see cref="Appointment"/> to update</param>
        /// <returns><see cref="Appointment"/></returns>
        /// <response code="200"><see cref="Appointment"/></response>
        /// <response code="404"> 
        /// No office found with id {officeId} &#10;  
        /// No appointment found with the id {appointmentId} for the office {office.Name}({officeId})
        /// </response>
        /// <response code="405"> You are required to have an <see cref="Appointment"/> in the request body </response>
        /// <response code="406"> No <see cref="Appointment"/> item found with id {appointment.Id} </response>
        [HttpPut("{officeId:int:required}"),
            ProducesResponseType(typeof(Appointment), (int)HttpStatusCode.OK),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.NotAcceptable),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.MethodNotAllowed),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.NotFound)]
        public IActionResult Put([Required] int officeId, [Required, FromBody] Appointment appointment)
        {
            var office = _officeRepository.Read(officeId);
            if (office == null) return new ContentResult
            {
                Content = $"No office found with id {officeId}",
                StatusCode = (int)HttpStatusCode.NotFound
            };
            if (appointment == null) return new ContentResult
            {
                Content = "You are required to have an appointment in the request body",
                StatusCode = (int)HttpStatusCode.MethodNotAllowed
            };

            try
            {
                var savedAppointment = _appointmentRepository.Update(appointment);
                return new ObjectResult(savedAppointment);
            }
            catch (ArgumentNullException e)
            {
                Console.WriteLine(e);
                return new ContentResult
                {
                    Content = e.Message,
                    StatusCode = (int)HttpStatusCode.NotAcceptable
                };
            }
            catch (ArgumentException e)
            {
                Console.WriteLine(e);
                return new ContentResult
                {
                    Content = e.Message,
                    StatusCode = (int)HttpStatusCode.NotFound
                };
            }
        }

        /// <summary>
        /// Deletes an <see cref="Appointment"/> by id
        /// </summary>
        /// <param name="appointmentId">The id of the <see cref="Appointment"/> to return</param>
        /// <returns><see cref="Appointment"/></returns>
        /// <response code="200">Deleted appointment with id {appointmentId}</response>
        /// <response code="400">Couldn't delete appointment with id {appointmentId}</response>
        /// <response code="404"> 
        /// No office found with id {officeId} &#10; 
        /// No appointment found with the id {appointmentId} for the office {office.Name}({officeId})
        /// </response>
        /// <response code="500"> Couldn't delete appointment with id {appointmentId}, reason: {reason} </response>
        [HttpDelete("{appointmentId:int:required}"),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.OK),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.BadRequest),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.NotFound),
            ProducesResponseType(typeof(string), (int)HttpStatusCode.InternalServerError)]
        public IActionResult Delete([Required] int appointmentId)
        {
            try
            {
                var isDeleted = _appointmentRepository.Delete(appointmentId);
                return isDeleted
                    ? new ContentResult
                    {
                        Content = $"Deleted appointment with id {appointmentId}",
                        StatusCode = (int) HttpStatusCode.OK
                    }
                    : new ContentResult
                    {
                        Content = $"Couldn't delete appointment with id {appointmentId}",
                        StatusCode = (int) HttpStatusCode.BadRequest
                    };
            }
            catch (ArgumentException e)
            {
                Console.WriteLine(e);
                return new ContentResult
                {
                    Content = e.Message,
                    StatusCode = (int) HttpStatusCode.NotFound
                };
            }
            catch (Exception e)
            {
                return new ContentResult
                {
                    Content = $"Couldn't delete appointment with id {appointmentId}, reason: {e.Message}",
                    StatusCode = (int)HttpStatusCode.InternalServerError
                };
            }
        }
    }
}