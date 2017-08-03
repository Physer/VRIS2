using System.Collections.Generic;
using VRIS.Domain.Models.Appointments;

namespace VRIS.Business.Repositories.Appointments
{
    /// <inheritdoc cref="IAppointmentRepository"/>
    public sealed class MockAppointmentRepository : MockRepository<Appointment>, IAppointmentRepository
    {
        /// <inheritdoc cref="IAppointmentRepository"/>
        public MockAppointmentRepository(IList<Appointment> mockList) : base(mockList) { }
    }
}
