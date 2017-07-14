using System.ComponentModel.DataAnnotations;

namespace VRIS.Domain.Models.Appointments
{
    /// <summary>
    /// Model displaying an appointment entry that has no Date and time scope
    /// </summary>
    public class Appointment
    {
        /// <summary>
        /// The unique identifier of the appointment
        /// </summary>
        [Required] public int Id { get; set; }

        /// <summary>
        /// The appointment subject
        /// </summary>
        [Required] public string Subject { get; set; }

        /// <summary>
        /// The OfficeId
        /// </summary>
        [Required] public int OfficeId { get; set; }

        /// <summary>
        /// Flag indicating whether or not this is an all day appointment (For Frontend)
        /// </summary>
        public virtual bool AllDay => true;
    }
}
