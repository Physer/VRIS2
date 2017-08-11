using System;

namespace VRIS.API.ViewModels
{
    /// <summary>
    /// Simple model containing a single url to the Authorization Page
    /// </summary>
    public class AuthorizationUrlViewModel
    {
        /// <summary>
        /// The url to the azure portal for this api
        /// </summary>
        public Uri AuthorizationUrl { get; set; }
    }
}
