using Microsoft.Graph;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VRIS.OutlookConnect
{
    // https://blog.jonathanhuss.com/intro-to-the-microsoft-graph-net-client-library/
    public class Test
    {
        private readonly IGraphServiceClient _client;
        public Test(IGraphServiceClient client)
        {
            _client = client;
        }

        public async Task<IEnumerable<User>> GetUserInfoAsync()
        {
            var users = await _client.Users.Request().GetAsync();
            return users.Where(user => user.UserType.Equals("room"));
        }
    }
}