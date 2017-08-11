namespace VRIS.Business.Repositories.Office
{
    /// <inheritdoc cref="IRepository{TModel}"/>
    public interface IOfficeRepository : IRepository<Domain.Models.Office> {
        /// <summary>
        /// Read an office that matches this name
        /// </summary>
        /// <param name="name"></param>
        /// <returns></returns>
        Domain.Models.Office FindByName(string name);
    }
}
