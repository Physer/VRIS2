using System;
using System.ComponentModel.DataAnnotations;

namespace VRIS.Domain.Models.Appointments
{
    /// <summary>
    /// Model displaying an appointment entry that has a Date and time scope
    /// </summary>
    public class SlotAppointment : Appointment
    {
        /// <summary>
        /// The Date and Time this appointment starts (In UTC)
        /// </summary>
        [Required] public DateTime StartUtc { get; set; }

        /// <summary>
        /// The Date and Time this appointment ends (In UTC)
        /// </summary>
        [Required] public DateTime EndUtc { get; set; }

        /// <inheritdoc cref="Appointment"/>
        public override bool AllDay => false;
    }
}
