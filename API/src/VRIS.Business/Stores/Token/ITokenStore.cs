namespace VRIS.Business.Stores.Token
{
    /// <summary>
    /// Store to hold the requested token
    /// </summary>
    public interface ITokenStore
    {
        /// <summary>
        /// The current statefull token
        /// </summary>
        string StoredToken { get; set; }
    }
}
