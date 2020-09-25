import session from './session'

const apiUrl = '/api'

export default {
  delivery_list() {
    return session.get(`${apiUrl}/delivery/`)
  },
}
