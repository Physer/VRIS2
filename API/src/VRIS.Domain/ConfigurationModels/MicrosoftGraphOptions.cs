namespace VRIS.Domain.ConfigurationModels
{
    public class MicrosoftGraphOptions
    {
        public string ClientId { get; set; }
        public string ClientSecret { get; set; }
        public string AuthContextUrl { get; set; }
        public string GraphUrl { get; set; }
        public string BearerToken { get; set; }
    }
}
