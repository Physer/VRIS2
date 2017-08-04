using System.ComponentModel.DataAnnotations;

namespace VRIS.Domain.Models
{
    /// <summary>
    /// Interface indicating this class has an Id property
    /// </summary>
    public interface IHasId
    {
        /// <summary>
        /// The unique identifier of the room
        /// </summary>
        [Required] int? Id { get; set; }
    }
}