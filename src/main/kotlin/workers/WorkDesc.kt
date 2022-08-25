package workers

import kotlinx.serialization.Serializable

// slide begin
@Serializable
data class WorkDesc(
  val jobId: Int,
  val timeRequired: Int
)
// slide end