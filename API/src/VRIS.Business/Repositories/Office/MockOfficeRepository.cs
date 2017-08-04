using System;
using System.Collections.Generic;
using System.Linq;

namespace VRIS.Business.Repositories.Office
{
    /// <inheritdoc cref="IOfficeRepository"/>
    public sealed class MockOfficeRepository : MockRepository<Domain.Models.Office>, IOfficeRepository
    {
        /// <inheritdoc cref="IOfficeRepository"/>
        public MockOfficeRepository(IList<Domain.Models.Office> mockList) : base(mockList) { }

        /// <inheritdoc />
        public Domain.Models.Office FindByName(string name) 
            => List().FirstOrDefault(item => item.Name.Equals(name, StringComparison.OrdinalIgnoreCase));
    }
}
