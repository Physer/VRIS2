namespace VRIS.Domain.ConfigurationModels
{
    /// <summary>
    /// Options containing configuration values for the VRIS project
    /// </summary>
    public class MicrosoftGraphOptions
    {
        /// <summary>
        /// The client ID
        /// </summary>
        public string ClientId { get; set; }

        /// <summary>
        /// The Secret key for the client
        /// </summary>
        public string ClientSecret { get; set; }

        /// <summary>
        /// The url pointing to microsoft authorization services common endpoint
        /// </summary>
        public string AuthContextUrl { get; set; }

        /// <summary>
        /// The url to microsoft graph
        /// </summary>
        public string GraphUrl { get; set; }
    }
}
