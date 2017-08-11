using System.ComponentModel.DataAnnotations;

namespace VRIS.Domain.Models
{
    /// <summary>
    /// Identity of a Meeting office
    /// </summary>
    public class Office : IHasId
    {
        /// <summary>
        /// The unique identifier of the room
        /// </summary>
        [Required] public int? Id { get; set; }

        /// <summary>
        /// The name of the room
        /// </summary>
        [Required] public string Name { get; set; }

        /// <summary>
        /// (future implementation) Venue of the office
        /// </summary>
        public int? VenueId { get; set; }
    }
}